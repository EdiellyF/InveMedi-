const BASE_URL = "http://localhost:8080/itens";

 async function callEndpoint(endpoint) {
    try {
        const response = await fetch(`${endpoint}`, {
            method: "GET",
            headers: {
                "Authorization": localStorage.getItem("Authorization"),
            },
        });

        if (!response.ok) {
            throw new Error(`Erro ao acessar ${endpoint}: ${response.status} ${response.statusText}`);
        }

        return await response.json();
    } catch (error) {
        console.error("Erro na chamada da API:", error);
        return null;
    }
}

function show(itens) {
    if (!itens || itens.length === 0) {
        console.log("Itens não carregados ou lista vazia");
        document.getElementById("itens").innerHTML = "<tr><td colspan='3'>Nenhum item encontrado</td></tr>";
        return;
    }

    console.log("Itens carregados:", itens);

    let tab = `
        <thead>
            <tr>
                <th scope="col">nome Item</th>
                <th scope="col">Quantidade Estoque</th>
                <th scope="col">Quantidade Mínima Estoque</th>
                <th scope="col">Açoes </th>
            </tr>
        </thead>
        <tbody>
            ${itens.map(item => `
                <tr class="row-${item.id}">
                    <td>${item.nomeItem}</td>
                    <td id="min-${item.id}">${item.quantidadeEstoque}</td>
                    <td > ${item.quantidadeMinima}</td>
                    <td><button  onclick="salvarEdicao(${item.id})" class="editar">editar</button>
                    <button id="${item.id}" onclick="excluirItem()" class="excluir">excluir</button></td>
                </tr>`).join('')}
        </tbody>
    `;


    document.getElementById("itens").innerHTML = tab;
}

async function getItens() {
    const data = await callEndpoint(`${BASE_URL}/user`);
    show(data);
}

document.addEventListener("DOMContentLoaded", function () {

    if (!localStorage.getItem("Authorization")) {
        window.location.href = "./login.html";
    } else {


        getItens();
        showName();

    }
});

 async function getName() {
    return await callEndpoint(`${BASE_URL}/username`);
}


async function showName(){
   const nome = document.querySelector('.welcome');
   const valueName = await getName();
   if (valueName){
       console.log(valueName)
       nome.innerText += " " + valueName;
   }
 }


 async function excluirItem() {
     if (confirm("Voce deseja excluir?")) {

         const botao = document.querySelector('.excluir');
         console.log(botao)
         const celula = botao.parentElement;
         const linha = celula.parentElement;
         linha.remove();
         try {
             const response = await fetch(`${BASE_URL}/${botao.id}`, {
                 method: "DELETE",
                 headers: {
                     "Authorization": localStorage.getItem("Authorization"),
                     "Content-Type": "application/json"
                 }
             });

             if (!response.ok) {
                 throw new Error("Erro ao excluir o item.");
             }

             linha.remove();
             console.log("Item removido com sucesso.");

         } catch (error) {
             console.error("Erro ao excluir:", error);
             alert("Erro ao excluir o item.");
         }


     }


 }




async function salvarEdicao(id) {
    const quantidadeTd = document.getElementById(`min-${id}`);
    const valorAtual = quantidadeTd.innerText.trim();

    quantidadeTd.innerHTML = `
        <input type="number" id="input-${id}" value="${valorAtual}" min="0">
        <button class="acoes-edicoes" onclick="confirmarEdicao(${id})">Salvar</button>
        <button class="acoes-edicoes" onclick="cancelarEdicao(${id}, '${valorAtual}')">Cancelar</button>
    `;

    desativarBotoes(id, true)

    document.getElementById(`input-${id}`).focus();
}

async function confirmarEdicao(id) {
    const input = document.getElementById(`input-${id}`);
    const novaQuantidade = parseInt(input.value, 10);

    if (isNaN(novaQuantidade) || novaQuantidade < 0) {
        alert("A quantidade mínima não pode ser negativa!");
        return;
    }

    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Authorization": localStorage.getItem("Authorization"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ quantidadeEstoque: novaQuantidade })
        });

        if (!response.ok) {
            throw new Error("Erro ao atualizar a quantidade mínima.");
        }
        document.getElementById(`min-${id}`).innerText = novaQuantidade;
        desativarBotoes(id, false)


    } catch (error) {
        console.error("Erro ao atualizar:", error);
        alert("Erro ao salvar edição.");
    }
}

function cancelarEdicao(id, valorAntigo) {
    document.getElementById(`min-${id}`).innerText = valorAntigo;
    desativarBotoes(id, false, 'blue')
}

function desativarBotoes(id, booleano){
    const linhas = document.querySelectorAll("#itens tbody tr");

    linhas.forEach(linha => {
        const editarBtn = linha.querySelector(".editar");
        const excluirBtn = linha.querySelector(".excluir");
        if (linha.id !== `row-${id}`) {
            if (editarBtn) {
                editarBtn.disabled = booleano;
                editarBtn.classList.toggle('editado', booleano)
            }

            if (excluirBtn) {
                excluirBtn.disabled = booleano;
                excluirBtn.classList.toggle('editado', booleano)
            }
        }
    });
}


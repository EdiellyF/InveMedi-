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
                <th scope="col">ID</th>
                <th scope="col">Quantidade Estoque</th>
                <th scope="col">Quantidade Mínima Estoque</th>
            </tr>
        </thead>
        <tbody>
            ${itens.map(item => `
                <tr>
                    <td>${item.id}</td>
                    <td>${item.quantidadeEstoque}</td>
                    <td>${item.quantidadeMinima}</td>
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
        window.location.href = "/view/login.html";
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

   console.log(valueName)
   nome.innerText += " " + valueName;
 }





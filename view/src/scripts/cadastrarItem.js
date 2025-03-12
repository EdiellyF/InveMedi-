const BASE_URL = "http://localhost:8080/itens";

async function cadastrarItem(event) {
    event.preventDefault();

    const nomeitem = document.getElementById('nomeitem').value;
    const quantidadeEstoque = document.getElementById('quantidadeEstoque').value;
    const quantidadeMinima = document.getElementById('quantidadeMinima').value;
    const dataValidade = document.getElementById('dataValidade').value;

    console.log(nomeitem)


    // Verifica se as quantidades são números válidos
    if (isNaN(quantidadeEstoque) || isNaN(quantidadeMinima)) {
        alert("As quantidades precisam ser números válidos.");
        return;
    }




    try {
        const response = await fetch(BASE_URL, {
            method: 'POST',
            headers: {
                "Authorization": localStorage.getItem("Authorization"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nomeitem: nomeitem,
                quantidadeEstoque: quantidadeEstoque,
                quantidadeMinima: quantidadeMinima,
                dataValidade: new Date(dataValidade).toISOString().split('T')[0]
            })
        });

        if (!response.ok) {
            throw new Error('Erro ao cadastrar item');
        }

        alert('Item cadastrado com sucesso!');
        document.getElementById('itemForm').reset(); // Limpa o formulário

        setTimeout(() => {
            window.location = "./index.html";
        }, 2000);


    } catch (error) {
        console.error('Erro ao cadastrar:', error);
        alert('Erro ao cadastrar item');
    }
}

document.addEventListener('DOMContentLoaded', function() {
    if (!localStorage.getItem('Authorization')) {
        window.location.href = './login.html'; // Redireciona para o login se não estiver autenticado
    } else {
        const form = document.getElementById('itemForm');
        if (form) {
            form.addEventListener('submit', cadastrarItem); // Associa o evento de envio do formulário
        }
    }
});

import {url} from "./api.js";



function  show(itens){
    let tab = `
            <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nome do Item</th>
                            <th>Quantidade em Estoque</th>
                            <th>Quantidade Mínima</th>
           
                        </tr>
            </thead>
    `;


    for(let item of itens){
        tab += `   <tbody>
                        <tr>
                            <td data-label="ID"> ${ item.id}</td>
                            <td data-label="Nome do Item"> ${item.nomeItem} </td>
                            <td data-label="Quantidade em Estoque">${item.quantidadeEstoque}</td>
                            <td data-label="Quantidade Mínima">${item.quantidadeMinima}</td>
                        </tr>
                    </tbody>`
    }

    document.getElementById("itens").innerHTML = tab;
}

async  function getApi(url){
    const response = await fetch(url, {method: "GET"});

    let data = await response.json();

    if (response){
        show(data);
    } else {
        alert("não há itens cadastrados!")
    }
}

getApi(url);
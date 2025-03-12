
const botaoCadastrar = document.querySelector(".cadastrar");

if (botaoCadastrar) {
    botaoCadastrar.addEventListener("click", handleLogin);
} else {
    console.error("Botão de cadastro não encontrado!");
}


async function handleLogin(event) {
    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const senha = document.getElementById("senha").value;
    const username = document.getElementById("nome").value.trim();
    try {
        const response = await fetch("http://localhost:8080/user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
            body: JSON.stringify({ email, username, password: senha })
        });

        if (!response.ok) {
            const errorData = await response.json().catch(() => null); // Tenta converter a resposta para JSON
            console.error("Erro na requisição:", errorData);

            const errorMessage = errorData?.message ||
                (response.status === 409 ? "Email ou nome de usuário já existem." : "Erro ao conectar ao servidor.");

            alert(errorMessage);
            return;
        }



        alert("Usuário cadastrado com sucesso!");
        setTimeout(() => {
            window.location = "./login.html";
        }, 2000);
    } catch (error) {
        console.error("Erro inesperado:", error);
        alert("Falha na conexão com o servidor.");
    }



}



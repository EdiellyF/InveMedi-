document.querySelector(".logar").addEventListener("click", login);

async function login(event) {
    event.preventDefault()
    let email = document.getElementById("email").value;
    let senha = document.getElementById("senha").value;

    console.log("Tentando logar com:", email, senha);

    try {
        const response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                Accept: "application/json",
            },
            body: JSON.stringify({
                email: email,
                password: senha,
            })
        });

        if (!response.ok) {
            throw new Error("Falha ao logar. Status: " + response.status);
        }

        let token = response.headers.get("Authorization");

        if (!token) {
            throw new Error("Token não recebido no cabeçalho da resposta!");
        }

        window.localStorage.setItem("Authorization", token);

        showToast("#okToast");

        setTimeout(() => {
            window.location = "./index.html";
        }, 2000);
    } catch (error) {
        console.error("Erro no login:", error);
        showToast("#errorToast")
    }
}

function showToast(id) {
    let toastElList = [].slice.call(document.querySelectorAll(id));
    let toastList = toastElList.map((toastEl) => new bootstrap.Toast(toastEl));
    toastList.forEach((toast) => toast.show());
}

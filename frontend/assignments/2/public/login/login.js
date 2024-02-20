document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch('http://localhost:3000/api/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });
        
        if (response.ok) {
            const data = await response.json();
            window.location.href = data.redirect_url;
        } else {
            const data = await response.json();
            alert(data.message || "An error occurred during login");
            document.getElementById('username').value = '';
            document.getElementById('password').value = '';
        }
    } catch (error) {
        console.error('Error:', error);
        alert("Incorrect username or password.");
        document.getElementById('username').value = '';
        document.getElementById('password').value = '';
    }
});

const userId = 1; // static user for now

// Load cart items
function loadCart() {
    fetch(`http://localhost:8081/cart/${userId}`)
        .then(res => res.json())
        .then(data => {
            const container = document.getElementById("cart-container");
            container.innerHTML = "";
            data.forEach(item => {
                container.innerHTML += `
                    <div style="border:1px solid black; margin:10px; padding:10px;">
                        <h3>${item.productName}</h3>
                        <p>Price: ₹${item.price}</p>
                        <button onclick="removeItem(${item.id})">Remove ❌</button>
                    </div>
                `;
            });
        });
}window.onload = loadCart;
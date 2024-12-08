const API_URL = '/api/products';

async function loadProducts() {
    const response = await fetch(API_URL);
    const products = await response.json();
    const productTableBody = document.querySelector('#productTable tbody');
    productTableBody.innerHTML = '';
    products.forEach(product => {
        productTableBody.innerHTML += `
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.quantity}</td>
                <td>${product.price}</td>
            </tr>
        `;
    });
}

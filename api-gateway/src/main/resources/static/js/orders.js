const API_URL = '/api/orders';

async function loadOrders() {
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ query: `{ orders { id status } }` })
    });
    const data = await response.json();
    const orders = data.data.orders;
    const orderTableBody = document.querySelector('#orderTable tbody');
    orderTableBody.innerHTML = '';
    orders.forEach(order => {
        orderTableBody.innerHTML += `
            <tr>
                <td>${order.id}</td>
                <td>${order.status}</td>
                <td>
                    <button onclick="deleteOrder(${order.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}

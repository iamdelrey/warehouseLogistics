const API_URL = '/api/orders'

async function loadOrders() {
	const response = await fetch(API_URL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ query: `{ orders { id status } }` }),
	})
	const data = await response.json()
	const orders = data.data.orders
	const orderTableBody = document.querySelector('#orderTable tbody')
	orderTableBody.innerHTML = ''
	orders.forEach(order => {
		orderTableBody.innerHTML += `
            <tr>
                <td class="border px-4 py-2">${order.id}</td>
                <td class="border px-4 py-2">${order.status}</td>
                <td class="border px-4 py-2">
                    <button onclick="deleteOrder(${order.id})"
                        class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700">
                        Удалить
                    </button>
                </td>
            </tr>
        `
	})
}

async function addOrder() {
	const status = document.getElementById('orderStatus').value
	const response = await fetch(API_URL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ status }),
	})
	if (response.ok) {
		loadOrders()
	} else {
		alert('Ошибка при добавлении заказа')
	}
}

async function deleteOrder(id) {
	const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' })
	if (response.ok) {
		loadOrders()
	} else {
		alert('Ошибка при удалении заказа')
	}
}

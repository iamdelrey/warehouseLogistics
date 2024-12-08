const API_URL = '/api/deliveries'

async function loadDeliveries() {
	const response = await fetch(API_URL)
	const deliveries = await response.json()
	const deliveryTableBody = document.querySelector('#deliveryTable tbody')
	deliveryTableBody.innerHTML = ''
	deliveries.forEach(delivery => {
		deliveryTableBody.innerHTML += `
            <tr>
                <td class="border px-4 py-2">${delivery.id}</td>
                <td class="border px-4 py-2">${delivery.orderId}</td>
                <td class="border px-4 py-2">${delivery.status}</td>
                <td class="border px-4 py-2">
                    <button onclick="deleteDelivery(${delivery.id})"
                        class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700">
                        Удалить
                    </button>
                </td>
            </tr>
        `
	})
}

async function addDelivery() {
	const orderId = document.getElementById('deliveryOrderId').value
	const status = document.getElementById('deliveryStatus').value
	const response = await fetch(API_URL, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ orderId, status }),
	})
	if (response.ok) {
		loadDeliveries()
	} else {
		alert('Ошибка при добавлении доставки')
	}
}

async function deleteDelivery(id) {
	const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' })
	if (response.ok) {
		loadDeliveries()
	} else {
		alert('Ошибка при удалении доставки')
	}
}

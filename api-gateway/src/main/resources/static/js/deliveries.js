const API_URL = '/api/deliveries'

async function loadDeliveries() {
	const response = await fetch(API_URL)
	const deliveries = await response.json()
	const deliveryTableBody = document.querySelector('#deliveryTable tbody')
	deliveryTableBody.innerHTML = ''
	deliveries.forEach(delivery => {
		deliveryTableBody.innerHTML += `
            <tr>
                <td>${delivery.id}</td>
                <td>${delivery.orderId}</td>
                <td>${delivery.status}</td>
                <td>
                    <button onclick="editDelivery(${delivery.id}, '${delivery.orderId}', '${delivery.status}')">Edit</button>
                    <button onclick="deleteDelivery(${delivery.id})">Delete</button>
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
		alert('Failed to add delivery')
	}
}

async function editDelivery(id, orderId, status) {
	const newOrderId = prompt('Enter new Order ID:', orderId)
	const newStatus = prompt('Enter new Status:', status)
	if (newOrderId && newStatus) {
		const response = await fetch(`${API_URL}/${id}`, {
			method: 'PUT',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ orderId: newOrderId, status: newStatus }),
		})
		if (response.ok) {
			loadDeliveries()
		} else {
			alert('Failed to update delivery')
		}
	}
}

async function deleteDelivery(id) {
	const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' })
	if (response.ok) {
		loadDeliveries()
	} else {
		alert('Failed to delete delivery')
	}
}

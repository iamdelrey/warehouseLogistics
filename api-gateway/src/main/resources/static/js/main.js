const BASE_URL = 'http://localhost:8080/api'

function router() {
	const hash = window.location.hash.slice(1)
	switch (hash) {
		case 'users':
			loadUsers()
			updateTitle('Пользователи')
			break
		case 'orders':
			loadOrders()
			updateTitle('Заказы')
			break
		case 'deliveries':
			loadDeliveries()
			updateTitle('Доставки')
			break
		case 'analytics':
			loadAnalytics()
			updateTitle('Аналитика')
			break
		default:
			showHome()
			updateTitle('Главная')
	}
}

function updateTitle(title) {
	document.title = `Warehouse Logistics - ${title}`
}

function showHome() {
	const main = document.getElementById('content')
	main.innerHTML = `
        <h2>Добро пожаловать!</h2>
        <p>Нажмите на раздел, чтобы посмотреть данные.</p>
    `
}

function loadUsers() {
	fetch(`${BASE_URL}/users`)
		.then(response => response.json())
		.then(data => {
			displayTable('Список пользователей', data, ['id', 'name', 'email'])
		})
		.catch(error => console.error('Ошибка при загрузке пользователей:', error))
}

function loadOrders() {
	fetch(`${BASE_URL}/orders`, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ query: '{ orders { id status } }' }),
	})
		.then(response => response.json())
		.then(data => {
			displayTable('Список заказов', data.data.orders, ['id', 'status'])
		})
		.catch(error => console.error('Ошибка при загрузке заказов:', error))
}

function loadDeliveries() {
	fetch(`${BASE_URL}/deliveries`)
		.then(response => response.json())
		.then(data => {
			displayTable('Список доставок', data, ['id', 'orderId', 'status'])
		})
		.catch(error => console.error('Ошибка при загрузке доставок:', error))
}

function loadAnalytics() {
	fetch(`${BASE_URL}/analytics/total-orders`)
		.then(response => response.json())
		.then(data => {
			const main = document.getElementById('content')
			main.innerHTML = `
                <h2>Общая аналитика</h2>
                <p>Общее количество заказов: ${data}</p>
            `
		})
		.catch(error => console.error('Ошибка при загрузке аналитики:', error))
}

function displayTable(title, data, columns) {
	const main = document.getElementById('content')
	let tableHtml = `<h2>${title}</h2>`
	tableHtml += '<table>'
	tableHtml += '<thead><tr>'
	columns.forEach(column => {
		tableHtml += `<th>${column}</th>`
	})
	tableHtml += '</tr></thead>'
	tableHtml += '<tbody>'
	data.forEach(item => {
		tableHtml += '<tr>'
		columns.forEach(column => {
			tableHtml += `<td>${item[column] || ''}</td>`
		})
		tableHtml += '</tr>'
	})
	tableHtml += '</tbody></table>'
	main.innerHTML = tableHtml
}

window.addEventListener('hashchange', router)
window.addEventListener('load', router)

const API_URL = '/api/analytics'

async function loadAnalytics() {
	try {
		const response = await fetch(`${API_URL}/total-orders`)
		if (!response.ok) {
			throw new Error(`Ошибка сервера: ${response.status}`)
		}

		const totalOrders = await response.text()
		const resultDiv = document.getElementById('analyticsResult')
		resultDiv.innerHTML = `
            <h3>Результаты аналитики</h3>
            <p>Общее количество заказов: <strong>${totalOrders}</strong></p>
        `
	} catch (error) {
		console.error('Ошибка при загрузке аналитики:', error)
		alert('Не удалось загрузить аналитику. Попробуйте позже.')
	}
}

const API_URL = '/api/users'

// Подключаем обработчики после загрузки страницы
window.addEventListener('DOMContentLoaded', () => {
	document.getElementById('loadUsersBtn').addEventListener('click', loadUsers)
	document.getElementById('addUserBtn').addEventListener('click', addUser)
})

async function loadUsers() {
	try {
		const response = await fetch(API_URL)
		if (!response.ok) {
			throw new Error(`Ошибка загрузки: ${response.statusText}`)
		}
		const users = await response.json()
		const userTableBody = document.querySelector('#userTable')
		userTableBody.innerHTML = ''
		users.forEach(user => {
			userTableBody.innerHTML += `
                <tr>
                    <td class="border px-4 py-2">${user.id}</td>
                    <td class="border px-4 py-2">${user.name}</td>
                    <td class="border px-4 py-2">${user.email}</td>
                    <td class="border px-4 py-2">
                        <button onclick="deleteUser(${user.id})" 
                            class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700">
                            Удалить
                        </button>
                    </td>
                </tr>
            `
		})
	} catch (error) {
		console.error('Ошибка при загрузке пользователей:', error)
	}
}

async function addUser() {
	try {
		const name = document.getElementById('userName').value
		const email = document.getElementById('userEmail').value
		const password = document.getElementById('userPassword').value
		const response = await fetch(API_URL, {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ name, email, password }),
		})
		if (!response.ok) {
			throw new Error(`Ошибка добавления: ${response.statusText}`)
		}
		await loadUsers()
	} catch (error) {
		console.error('Ошибка при добавлении пользователя:', error)
	}
}

async function deleteUser(userId) {
	try {
		const response = await fetch(`${API_URL}/${userId}`, { method: 'DELETE' })
		if (!response.ok) {
			throw new Error(`Ошибка удаления: ${response.statusText}`)
		}
		await loadUsers()
	} catch (error) {
		console.error('Ошибка при удалении пользователя:', error)
	}
}

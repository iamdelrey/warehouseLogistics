const API_URL = '/api/users'

async function loadUsers() {
	const response = await fetch(API_URL)
	const users = await response.json()
	const userTableBody = document.querySelector('#userTable tbody')
	userTableBody.innerHTML = ''
	users.forEach(user => {
		userTableBody.innerHTML += `
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>
                    <button onclick="editUser(${user.id}, '${user.name}', '${user.email}')">Edit</button>
                    <button onclick="deleteUser(${user.id})">Delete</button>
                </td>
            </tr>
        `
	})
}

async function addUser() {
	const name = document.getElementById('userName').value
	const email = document.getElementById('userEmail').value
	const password = document.getElementById('userPassword').value

	if (!name || !email || !password) {
		alert('All fields are required!')
		return
	}

	const response = await fetch('/api/users', {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ name, email, password }),
	})

	if (response.ok) {
		alert('User added successfully!')
		loadUsers()
	} else {
		const error = await response.json()
		alert(`Error: ${error.message}`)
	}
}

async function editUser(id, name, email) {
	const newName = prompt('Enter new name:', name)
	const newEmail = prompt('Enter new email:', email)
	if (newName && newEmail) {
		const response = await fetch(`${API_URL}/${id}`, {
			method: 'PUT',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				name: newName,
				email: newEmail,
				password: 'default_password',
			}),
		})
		if (response.ok) {
			loadUsers()
		} else {
			alert('Failed to update user')
		}
	}
}

async function deleteUser(userId) {
	const response = await fetch(`${API_URL}/${userId}`, { method: 'DELETE' })
	if (response.ok) {
		loadUsers()
	}
}

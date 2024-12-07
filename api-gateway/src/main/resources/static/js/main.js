// URL API Gateway (если запускается через API Gateway)
const BASE_URL = 'http://localhost:8080/api';

// Обработка нажатия на меню
document.getElementById('btn-users').addEventListener('click', () => {
    loadUsers();
});

document.getElementById('btn-orders').addEventListener('click', () => {
    loadOrders();
});

document.getElementById('btn-deliveries').addEventListener('click', () => {
    loadDeliveries();
});

document.getElementById('btn-analytics').addEventListener('click', () => {
    loadAnalytics();
});

// Загрузка пользователей
function loadUsers() {
    fetch(`${BASE_URL}/users`)
        .then(response => response.json())
        .then(data => {
            displayTable('Список пользователей', data, ['id', 'name', 'email']);
        })
        .catch(error => console.error('Ошибка при загрузке пользователей:', error));
}

// Загрузка заказов
function loadOrders() {
    fetch(`${BASE_URL}/orders`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ query: '{ orders { id status } }' })
    })
    .then(response => response.json())
    .then(data => {
        displayTable('Список заказов', data.data.orders, ['id', 'status']);
    })
    .catch(error => console.error('Ошибка при загрузке заказов:', error));
}

// Загрузка доставок
function loadDeliveries() {
    fetch(`${BASE_URL}/deliveries`)
        .then(response => response.json())
        .then(data => {
            displayTable('Список доставок', data, ['id', 'orderId', 'status']);
        })
        .catch(error => console.error('Ошибка при загрузке доставок:', error));
}

// Загрузка аналитики
function loadAnalytics() {
    fetch(`${BASE_URL}/analytics/total-orders`)
        .then(response => response.json())
        .then(data => {
            const main = document.getElementById('content');
            main.innerHTML = `
                <h2>Общая аналитика</h2>
                <p>Общее количество заказов: ${data}</p>
            `;
        })
        .catch(error => console.error('Ошибка при загрузке аналитики:', error));
}

// Универсальная функция для отображения таблицы
function displayTable(title, data, columns) {
    const main = document.getElementById('content');
    let tableHtml = `<h2>${title}</h2>`;
    tableHtml += '<table>';
    tableHtml += '<thead><tr>';
    columns.forEach(column => {
        tableHtml += `<th>${column}</th>`;
    });
    tableHtml += '</tr></thead>';
    tableHtml += '<tbody>';
    data.forEach(item => {
        tableHtml += '<tr>';
        columns.forEach(column => {
            tableHtml += `<td>${item[column] || ''}</td>`;
        });
        tableHtml += '</tr>';
    });
    tableHtml += '</tbody></table>';
    main.innerHTML = tableHtml;
}

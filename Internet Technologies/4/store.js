var users = [];
users.push('Armor Tiki');
users.push('Flier Tiki');

var JSONreadyUsers = JSON.stringify(users);

localstorage.setItem('users', JSONreadyUsers);
console.log(JSON.parse(localStorage['users']));
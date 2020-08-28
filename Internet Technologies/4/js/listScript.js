function myfunction()
{
var users = [];
users.push('Armor Tiki');
users.push('Flier Tiki');

var JSONreadyUsers = JSON.stringify(users);

if(window.localStorage) 
{
  // localStorage can be used

localstorage.setItem('users', JSONreadyUsers);
console.log(JSON.parse(localStorage['users']));
} 
else 
{
  // can't be used
}
}
<button onclick = "addToList()"> Add to Wishlist </button>

<script>
// When you get more student information, you should:
function addToList()
{
    var list = JSON.parse(localStorage.getItem('listInfo') || [];
    // add to it,
    list.push("Flier Tiki");
    // then put it back.
    localStorage.setItem("listInfo", JSON.stringify(list));
}
</script>
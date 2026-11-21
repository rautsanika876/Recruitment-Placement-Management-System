function quickSearch(input, tableId){
 const term=input.value.toLowerCase(), table=document.getElementById(tableId);
 if(!table)return;
 [...table.tBodies[0].rows].forEach(row=>row.style.display=row.innerText.toLowerCase().includes(term)?"":"none");
}
function filterTable(tableId){
 const table=document.getElementById(tableId);
 if(!table){alert("This is a static prototype. Use the quick search field where available.");return;}
 const term=prompt("Enter text to search in the table (e.g. Java, OPEN):");
 if(term===null)return;
 [...table.tBodies[0].rows].forEach(row=>row.style.display=row.innerText.toLowerCase().includes(term.toLowerCase())?"":"none");
}
function resetFilters(){document.querySelectorAll("input,select,textarea").forEach(el=>{if(el.type!=="button"&&el.type!=="submit")el.value=""});document.querySelectorAll("tbody tr").forEach(row=>row.style.display="");}

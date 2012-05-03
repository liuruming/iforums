function toggle_collapse(objId) {
	var obj = document.getElementById(objId);
	obj.style.display = obj.style.display == '' ? 'none' : '';

	var img = $(objId + '_img');
	if (img.src.indexOf('_yes.gif') == -1)
		img.src = img.src.replace(/_no\.gif/, '_yes\.gif');
	else
		img.src = img.src.replace(/_yes\.gif/, '_no\.gif');
}
const apiDomain = 'http://localhost:8000/api';
async function callAPI(url, method, headers, data) {
    const option = {};
    let response = null;
    let responseBody = null;
    
    option.method = method.toUpperCase();

    if (!!headers) option.headers = headers;
    if (!!data) option.body = data;

    response = await fetch(url, option);
    responseBody = await response.text(); 
    
    if (response.bodyUsed && !!responseBody) {
        responseBody = JSON.parse(responseBody); 
    }

    if (response.ok) {
        return responseBody;
    } else {
        throw new Error(responseBody.message);
    }
}
async function loadFixedExtensionList() {
    try {
        return await callAPI(apiDomain + '/fixed-extension', 'get');
    } catch(error) {
        console.error(error);
        alert(error.message);
    }
}
async function changeFixedExtension(event) {
    try {
        await callAPI(apiDomain + '/fixed-extension/' + event.target.value 
        	+ ((event.target.checked) ? '/check' : '/uncheck'), 'put');
    } catch(error) {
        console.error(error);
        event.target.checked = !event.target.checked;
        alert(error.message);
    }
}
async function addCustomExtension(extension) {
    try {
        return await callAPI(apiDomain + '/custom-extension', 'post',{
            'Content-Type': 'text/plain'
        } , extension);
    } catch(error) {
        console.error(error);
        alert(error.message);
    }
}
async function loadCustomExtensionList() {
    try {
        return await callAPI(apiDomain + '/custom-extension', 'get');
    } catch(error) {
        console.error(error);
        alert(error.message);
    }
}
async function clickCustomExtensionRemoveBtn(event) {
    try {
        const extensionNo = event.target.getAttribute('data-no');
        await callAPI(apiDomain + '/custom-extension/' + extensionNo, 'delete');
        document.getElementById('cutom-extension-' + extensionNo).remove();
    } catch(error) {
        console.error(error);
        alert(error.message);
    }
}
function drawFixedExtensionList(fixedExtensionList) {
    const checkboxWrapper = document.getElementById('checkbox-wrapper');
    checkboxWrapper.innerHTML = ''; 

    for (let i = 0; i < fixedExtensionList.length; i++) {
        const fixedExtension = fixedExtensionList[i];
        const div = document.createElement('div');
        const input = document.createElement('input');
        const label = document.createElement('label');

        div.classList.add('form-check');
        div.classList.add('form-check-inline');
        input.classList.add('form-check-input');
        label.classList.add('form-check-label');

        input.setAttribute('type', 'checkbox');
        input.setAttribute('id', 'fixedEstension-' + fixedExtension.no);
        input.setAttribute('value', fixedExtension.no);
        input.addEventListener('change', changeFixedExtension);
        
        if (fixedExtension.state.toUpperCase() === 'CHECKED') {
            input.setAttribute('checked', 'checked');
        }

        label.innerText = fixedExtension.extension;
        label.setAttribute('for', 'fixedEstension-' + fixedExtension.no);

        div.appendChild(input);
        div.appendChild(label);

        checkboxWrapper.appendChild(div);
    } 
}
function drawCustomExtension(customExtension) {
    const span = document.createElement('span');
    const closeBtn = document.createElement('i');

    span.classList.add('cutom-extension');
    closeBtn.classList.add('fas');
    closeBtn.classList.add('fa-times');
    
    span.innerText = customExtension.extension;

    span.setAttribute('id', 'cutom-extension-' + customExtension.no);

    closeBtn.setAttribute('data-no', customExtension.no);
    closeBtn.addEventListener('click', clickCustomExtensionRemoveBtn);

    span.appendChild(closeBtn);

    return span;
}
function drawCustomExtensionList(customExtensionList) {
    const customExtensionWrapper = document.getElementById('custom-extension-wrapper');
    customExtensionWrapper.innerHTML = '';

    console.log(customExtensionList);

    for (let i = 0; i < customExtensionList.length; i++) {
        customExtensionWrapper.appendChild(drawCustomExtension(customExtensionList[i]));
    } 
}

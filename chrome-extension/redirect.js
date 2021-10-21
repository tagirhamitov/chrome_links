const localhost = 'http://localhost:57335/';

function redirect(request) {
    const prefix = 'http://';
    const path = request.url.slice(prefix.length);

    const parts = path.split("/");
    
    if (parts[0].includes(".")) {
        return { redirectUrl: prefix + path };
    }

    if (parts[0] === "localhost" || parts[0].startsWith("localhost:")) {
        return { redirectUrl: prefix + path };
    }
    
    if (["all", "del", "add"].includes(parts[0])) {
        return { redirectUrl: localhost + path };
    }

    return { redirectUrl: localhost + 'short/' + path };
}

chrome.webRequest.onBeforeRequest.addListener(redirect, { urls: ['http://*/*'] }, ['blocking']);

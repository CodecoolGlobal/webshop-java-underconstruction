export class ApiConnector {

    static _api_get (url, callback) {
        // loads data from API, parses it and calls the callback with it

        fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
            .then(response => response.json())  // parse the response as JSON
            .then(json_response => callback(json_response));  // Call the `callback` with the returned object
    }

    static _api_post (url, data, callback) {
        // sends the data to the API, and calls callback function

        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            headers: new Headers({
                'content-type': 'application/json'
            }),
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(json_response => callback(json_response));

    }

    static _api_post_with_status_response (url, data, callback) {
        // sends the data to the API, and calls callback function

        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            headers: new Headers({
                'content-type': 'application/json'
            }),
            body: JSON.stringify(data)
        })
            .then(response => callback(response.status))
    }

    static _api_put (url, data, callback){

        return fetch(url, {
            method: 'PUT',
            credentials: 'same-origin',
            headers: new Headers({
                'content-type': 'application/json'
            }),
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(json => callback(json));
    }

    static _api_delete(url, callback) {
        fetch(url, {
            method: 'DELETE',
            credentials: 'same-origin'
        }).then(response => response.json())
            .then(json_response => callback(json_response));
    }
}
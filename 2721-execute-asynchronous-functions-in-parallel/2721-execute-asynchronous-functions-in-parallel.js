
var promiseAll = function(functions) {
    return new Promise((resolve, reject) => {
        const n = functions.length;
        const results = new Array(n);
        let completed = 0;

        functions.forEach((fn, index) => {
            fn().then(value => {
                results[index] = value;
                completed++;

                if (completed === n) {
                    resolve(results);
                }
            }).catch(error => {
                reject(error);
            });
        });
    });
};

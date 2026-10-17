var chunk = function(arr, size) {
    const result = [];

    for (let i = 0; i < arr.length; i += size) {
        const current = [];

        for (let j = i; j < Math.min(i + size, arr.length); j++) {
            current.push(arr[j]);
        }

        result.push(current);
    }

    return result;
};
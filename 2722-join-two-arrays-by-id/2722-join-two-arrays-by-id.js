
var join = function(arr1, arr2) {
    const result = [];

    for (const obj1 of arr1) {
        let merged = { ...obj1 };

        for (const obj2 of arr2) {
            if (obj1.id === obj2.id) {
                merged = { ...obj1, ...obj2 };
                break;
            }
        }

        result.push(merged);
    }

    for (const obj2 of arr2) {
        let found = false;

        for (const obj1 of arr1) {
            if (obj1.id === obj2.id) {
                found = true;
                break;
            }
        }

        if (!found) {
            result.push(obj2);
        }
    }

    return result.sort((a, b) => a.id - b.id);
};

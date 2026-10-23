var compactObject = function(obj) {
    if (Array.isArray(obj)) {
        const result = [];

        for (const value of obj) {
            if (Boolean(value)) {
                result.push(compactObject(value));
            }
        }

        return result;
    }

    if (obj !== null && typeof obj === "object") {
        const result = {};

        for (const key in obj) {
            if (Boolean(obj[key])) {
                result[key] = compactObject(obj[key]);
            }
        }

        return result;
    }

    return obj;
};
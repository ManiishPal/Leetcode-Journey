Array.prototype.groupBy = function (fn) {
    const result = {};
    const keys = new Set(this.map(item => fn(item)));

    for (const key of keys) {
        result[key] = this.filter(item => fn(item) === key);
    }

    return result;
};
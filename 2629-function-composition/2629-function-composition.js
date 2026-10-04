var compose = function(functions) {
    return function(x) {
        let result = x;

        let reversed = [...functions].reverse();

        for (let fn of reversed) {
            result = fn(result);
        }

        return result;
    };
};
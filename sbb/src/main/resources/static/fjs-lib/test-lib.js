// 1-1
function addMaker(a) {
    return function (b) {
        return a + b;
    }
}

// 1-5
const users = [
    { name: 'ID', age: 32 },
    { name: 'BJ', age: 12 },
    { name: 'JM', age: 31 },
    { name: 'PJ', age: 24 },
    { name: 'HA', age: 41 },
    { name: 'JE', age: 32 }
];

// 1.2.2
function filter(list, predicate) {
    var new_list = [];
    for (var i = 0; i < list.length; i++) {
        if (predicate(list[i])) new_list.push(list[i]);
    }
    return new_list;
}
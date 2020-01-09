const sortItems = (items) =>  items.sort((a, b) => new Date(swapDayAndMonth(b.date)) - new Date(swapDayAndMonth(a.date)))

const swapDayAndMonth = (date) => {
    let array = date.split("/")
    let result =  `${array[1]}/${array[0]}/${array[2]}`
    return result
}

module.exports = sortItems;
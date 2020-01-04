const sortItems = (items) => {
    items =  items.sort((a, b) => {
        return new Date(b.date) - new Date(a.date)
    })
    return items
}

module.exports = sortItems;
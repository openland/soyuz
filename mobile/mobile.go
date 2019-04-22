package soyuz

import "github.com/openland/soyuz/storage"

var nexID = 0
var stores = make(map[int]*storage.SoyuzStorage)

func StoreCreate(name string) int {
	id := nexID
	nexID++
	res := storage.NewStorage(name)
	stores[id] = res
	return id
}

func StoreWrite(id int, key string, value string) {
	stores[id].WriteKey(key, value)
}

func StoreRead(id int, key string) string {
	return stores[id].ReadKey(key)
}

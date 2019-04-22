package storage

type SoyuzStorage struct {
	values map[string]string
}

func NewStorage(name string) *SoyuzStorage {
	return &SoyuzStorage{values: make(map[string]string)}
}

func (store *SoyuzStorage) WriteKey(key string, value string) {
	store.values[key] = value
}

func (store *SoyuzStorage) DeleteKey(key string) {
	delete(store.values, key)
}

func (store *SoyuzStorage) Clear() {
	store.values = make(map[string]string)
}

import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { TodoItem, addItem } from '../redux/ToDoListSlice';

export const useLocalStorageItems = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    const storedItems = localStorage.getItem('todoItems');
    if (storedItems) {
      try {
        const parsedItems: string[] = JSON.parse(storedItems).map((item: TodoItem) => item.name);
        if (Array.isArray(parsedItems)) {
          parsedItems.forEach(item => dispatch(addItem(item)));
        } else {
          console.error('Stored items is not an array:', storedItems);
        }
      } catch (error) {
        console.error('Error parsing stored items:', error);
      }
    }
  }, [dispatch]);

  const saveItemsToLocalStorage = (items: TodoItem[]) => {
    localStorage.setItem('todoItems', JSON.stringify(items));
  };

  return { saveItemsToLocalStorage };
};

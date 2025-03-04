import Dexie, { Table } from 'dexie'
import { City } from '../models'

export class AppDB extends Dexie {
    //cities!: Table<City, number>    // <Obj as schema, primary key>
    cities!: Table<City, string>    // <Obj as schema, primary key>

    constructor() {
        super('AppDB')  // DB name
        // this.version(1).stores({
        //     cities: '++id, code, name'
        // })
        this.version(1).stores({
            cities: 'code, name'
        })
    }

    async addCity(item: City) {
        // const cityListId = await db.cities.add(item)
        // console.log('City added with id: ', cityListId)
        await db.cities.put(item)   // adds item if it doesnt exist (by checking pri key)
        console.log('City added city.code: ', item.code)
    }
}

export const db = new AppDB();
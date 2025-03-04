export interface UploadResult {
    postId: string
    image: string
}

export interface City {
    //id?: number             // Note: need to declare ? for auto mapping since backend doesnt have this field 
    code: string
    name: string
}
package main

import "io"
import "log"
import "os"
import "net/http"
import "html/template"
import "io/ioutil"

const (
  UPLOAD_DIR = "./uploads"
)
func uploadHandler(w http.ResponseWriter,r *http.Request){
  if r.Method =="GET" {
    t,_ :=template.ParseFiles("upload.html")
    t.Execute(w,nil)
  }else {
    f,h,_ :=r.FormFile("image")
    filename :=h.Filename
    defer f.Close()

    t,_ :=os.Create(UPLOAD_DIR+ "/" +filename)
    defer t.Close()

    _,err :=io.Copy(t,f)
    if err != nil{
      log.Fatal("uploadHandler:",err.Error())
      return
    }
    http.Redirect(w,r,"view?id="+filename,http.StatusFound)
  }
}
func viewHandler(w http.ResponseWriter,r *http.Request){
  imageId := r.FormValue("id")
  imagePath := UPLOAD_DIR+"/"+imageId
  w.Header().Set("Content-Type","image")
  http.ServeFile(w,r,imagePath)
}
func listHandler(w http.ResponseWriter,r *http.Request){
  fileInfoArr,_ := ioutil.ReadDir(UPLOAD_DIR)
  locals := make(map[string] interface{})
  images :=[]string{}
  for _,fileInfo :=range fileInfoArr{
    images = append(images,fileInfo.Name())
  }
  locals["images"] = images
  t,_ := template.ParseFiles("list.html")
  t.Execute(w,locals)
}
func main(){
  http.HandleFunc("/upload",uploadHandler)
  http.HandleFunc("/view",viewHandler)
  http.HandleFunc("/",listHandler)
  err :=http.ListenAndServe(":9999",nil)
  if err != nil{
    log.Fatal("ListenAndServe:",err.Error())
  }
}

package com.example.library.Controllers;


import com.example.library.Entities.BookEntity;
import com.example.library.Entities.EditorEntity;
import com.example.library.Services.BookService;
import com.example.library.Services.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/editors")
public class EditorController {

    @Autowired
    private EditorService editorService;

    @Autowired
    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @RequestMapping(value = "/show", method = {RequestMethod.GET})
    public ResponseEntity<String> getStatus() {
        System.out.println("Hello");
        return new ResponseEntity<String>("Hello world", HttpStatus.OK);
    }

    @RequestMapping(value="/all", method = { RequestMethod.GET })
    public Iterable<EditorEntity> getEditors() {
        Iterable<EditorEntity> list = editorService.findEditors();
        return list;
    }

    @RequestMapping(value="/{id}", method = { RequestMethod.GET })
    public ResponseEntity<EditorEntity> getEditorById(@PathVariable("id") int editorId) {
        EditorEntity editor = editorService.editorById(editorId);
        return new ResponseEntity<>(editor,HttpStatus.OK);

    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> insertEditor(@RequestBody EditorEntity editor )
    {
        int editorID = editorService.insert(editor);
        return new ResponseEntity<>(editorID,HttpStatus.CREATED);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatedEdior(@RequestBody EditorEntity editor, @PathVariable int id)
    {
        EditorEntity editorUpdate=editorService.update(editor,id);
        return new ResponseEntity<>(editorUpdate,HttpStatus.OK);
    }
    @RequestMapping(value="/delete/{id}", method={RequestMethod.DELETE})
    @ResponseBody
    public String deleteEditor(@PathVariable int id)
    {
        editorService.deleteEditorById(id);
        return "Editor  has been deleted successfully";
    }

}

package com.example.library.Services;

import com.example.library.Entities.AuthorEntity;
import com.example.library.Entities.BookEntity;
import com.example.library.Entities.EditorEntity;
import com.example.library.Exceptions.ResourceNotFoundException;
import com.example.library.Repositories.AuthorRepository;
import com.example.library.Repositories.BookRepository;
import com.example.library.Repositories.EditorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditorService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private EditorRepository editorRepository;
    private EditorEntity editorEntity;

    public EditorService (EditorRepository editorRepository)
    {
        this.editorRepository=editorRepository;
    }

    public Iterable<EditorEntity>  findEditors(){return editorRepository.findAll();};

    public EditorEntity editorById(int id) {
        Optional<EditorEntity> editor =  editorRepository.findById(id);
        if (!editor.isPresent()) {
            LOGGER.error("Editor with id {} was not found ", id);
            throw new ResourceNotFoundException(id);
        }
        return  editor.get();
    }
    public int  insert(EditorEntity editor)
    {
        EditorEntity neditor =editorRepository.save(editor);
        LOGGER.debug("Editor with id {} was  inserted in db", neditor.getId());
        return neditor.getId();
    }

    public EditorEntity update(EditorEntity newEditor, int id) {
        Optional<EditorEntity> updatedEditor = editorRepository.findById(id);
        if (!updatedEditor.isPresent()) {
            LOGGER.error("Editor  with id {} was not found", id);
            throw new ResourceNotFoundException(id);
        }

        newEditor.setId(id);
        return editorRepository.save(newEditor);
    }

        public void  deleteEditorById(int id)
        {
            editorRepository.deleteById(id);
        }
}


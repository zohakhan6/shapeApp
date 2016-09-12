package com.shape.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shape.app.model.Rectangle;
import com.shape.app.model.User;
import com.shape.app.service.RectangleService;

@RestController
public class RectangleController {

    @Autowired
    private RectangleService service;

    @RequestMapping(value = "/rectangle/", method = RequestMethod.GET)
    public ResponseEntity<List<Rectangle>> listAllRectangles() {
        List<Rectangle> Rectangles = service.findAllRectangles();
        if (Rectangles.isEmpty()) {
            return new ResponseEntity<List<Rectangle>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Rectangle>>(Rectangles, HttpStatus.OK);
    }

    @RequestMapping(value = "/rectangle/", method = RequestMethod.POST)
    public ResponseEntity<Void> createRectangle(@RequestBody Rectangle rectangle, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Rectangle with breadth " + rectangle.getBreadth() + " ,length :" + rectangle.getLength());

        if (service.isRectangleExist(rectangle)) {
            System.out.println("A Rectangle with dimensions " + rectangle.getLength() + "*" + rectangle.getBreadth() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.saveRectangle(rectangle);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rectangle/{dimensions}").buildAndExpand(rectangle.getDimensions()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rectangle/{dimensions}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteRectangle(@PathVariable("dimensions") String dimensions) {
        System.out.println("Fetching & Deleting Rectangle with dimensions " + dimensions);

        Rectangle user = service.findByDimensions(dimensions);
        if (user == null) {
            System.out.println("Unable to delete. Rectangle with dimensions " + dimensions + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        service.deleteRectangleByDimensions(dimensions);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}

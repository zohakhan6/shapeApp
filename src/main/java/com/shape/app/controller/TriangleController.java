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

import com.shape.app.model.Triangle;
import com.shape.app.model.User;
import com.shape.app.service.TriangleService;

@RestController
public class TriangleController {
    @Autowired
    private TriangleService service;

    @RequestMapping(value = "/triangle/", method = RequestMethod.GET)
    public ResponseEntity<List<Triangle>> listAllTriangles() {
        List<Triangle> Triangles = service.findAllTriangles();
        if (Triangles.isEmpty()) {
            return new ResponseEntity<List<Triangle>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Triangle>>(Triangles, HttpStatus.OK);
    }

    @RequestMapping(value = "/triangle/", method = RequestMethod.POST)
    public ResponseEntity<Void> createTriangle(@RequestBody Triangle triangle, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Triangle with dimension " + triangle.getDimensions());

        if (service.isTriangleExist(triangle)) {
            System.out.println("A Triangle with dimensions " + triangle.getLength() + "*" + triangle.getBreadth() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.saveTriangle(triangle);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/triangle/{dimensions}").buildAndExpand(triangle.getDimensions()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/triangle/{dimensions}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("dimensions") String dimensions) {
        System.out.println("Fetching & Deleting Triangle with dimensions " + dimensions);

        Triangle user = service.findByDimensions(dimensions);
        if (user == null) {
            System.out.println("Unable to delete. Triangle with dimensions " + dimensions + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        service.deleteTriangleByDimensions(dimensions);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}

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

import com.shape.app.model.Circle;
import com.shape.app.model.User;
import com.shape.app.service.CircleService;

@RestController
public class CircleController {
    @Autowired
    private CircleService service;

    @RequestMapping(value = "/circle/", method = RequestMethod.GET)
    public ResponseEntity<List<Circle>> listAllCircles() {
        List<Circle> Circles = service.findAllCircles();
        if (Circles.isEmpty()) {
            return new ResponseEntity<List<Circle>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Circle>>(Circles, HttpStatus.OK);
    }

    @RequestMapping(value = "/circle/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCircle(@RequestBody Circle circle, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Circle with radius " + circle.getRadius());

        if (service.isCircleExist(circle)) {
            System.out.println("A Circle with radius " + circle.getRadius() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.saveCircle(circle);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/circle/{dimensions}").buildAndExpand(Circle.getDimensions()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/circle/{radius}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteCircle(@PathVariable("radius") int radius) {
        System.out.println("Fetching & Deleting Circle with radius " + radius);

        Circle user = service.findByRadius(radius);
        if (user == null) {
            System.out.println("Unable to delete. Circle with dimensions " + radius + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        service.deleteCircleByRadius(radius);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}

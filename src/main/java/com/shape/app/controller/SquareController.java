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

import com.shape.app.model.Square;
import com.shape.app.model.User;
import com.shape.app.service.SquareService;

@RestController
public class SquareController {
    @Autowired
    private SquareService service;

    @RequestMapping(value = "/square/", method = RequestMethod.GET)
    public ResponseEntity<List<Square>> listAllSquares() {
        List<Square> Squares = service.findAllSquares();
        if (Squares.isEmpty()) {
            return new ResponseEntity<List<Square>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Square>>(Squares, HttpStatus.OK);
    }

    @RequestMapping(value = "/square/", method = RequestMethod.POST)
    public ResponseEntity<Void> createSquare(@RequestBody Square square, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Square with side " + square.getSide());

        if (service.isSquareExist(square)) {
            System.out.println("A Square with side " + square.getSide());
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        service.saveSquare(square);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/square/{side}").buildAndExpand(square.getSide()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/square/{side}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteSquare(@PathVariable("dimensions") int side) {
        System.out.println("Fetching & Deleting Square with dimensions " + side);

        Square user = service.findByDimensions(side);
        if (user == null) {
            System.out.println("Unable to delete. Square with side " + side + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        service.deleteSquareByDimensions(side);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}

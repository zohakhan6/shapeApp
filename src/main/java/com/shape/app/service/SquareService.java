package com.shape.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.app.dao.SquareDao;
import com.shape.app.dao.model.SquareDocument;
import com.shape.app.model.Square;

@Service
public class SquareService {

    @Autowired
    private SquareDao squaredao;

    public List<Square> findAllSquares() {
        List<SquareDocument> SquareList = squaredao.getAll();
        List<Square> Squares = new ArrayList<Square>();
        for (SquareDocument data : SquareList) {
            Square Square = new Square(Integer.valueOf(data.getSide()));
            Squares.add(Square);
        }
        return Squares;
    }

    public boolean isSquareExist(Square square) {
        SquareDocument data = squaredao.getSquareData(square.getSide());
        return data != null;
    }

    public void saveSquare(Square square) {
        SquareDocument data = new SquareDocument(String.valueOf(square.getSide()));
        squaredao.save(data);

    }

    public Square findByDimensions(int side) {
        SquareDocument data = squaredao.getSquareData(side);
        return new Square(Integer.valueOf(data.getSide()));
    }

    public void deleteSquareByDimensions(int side) {
        squaredao.delete(side);

    }
}

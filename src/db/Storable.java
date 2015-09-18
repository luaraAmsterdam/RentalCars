/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Laura
 */
public interface Storable {
    void save();
    void remove();
    void update();
}

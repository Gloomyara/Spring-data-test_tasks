package ru.antonovmikhail.jdbc.abstractions;

import java.util.List;
import java.util.Optional;

public interface Repository<K,V> {

    void containsOrElseThrow(K k);

    V save(V v);

    V update(V v);

    Optional<V> findById(K k);

    List<V> findAll();

    Optional<V> deleteById(K k);
}

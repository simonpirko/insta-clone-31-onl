package by.tms.instaclone31onl.repositories;

import by.tms.instaclone31onl.core.interfaces.entities.CsvEntity;
import by.tms.instaclone31onl.core.models.csv.CsvTable;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class InstaRepository<T extends CsvEntity> {

    private final CsvTable<T> table;

    public InstaRepository(CsvTable<T> table) {
        this.table = table;
    }

    protected abstract T mapper(String[] line);

    public List<T> getAll(Predicate<T> filter) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(table.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            List<T> entities = new ArrayList<>();
            Iterator<String[]> interator = csvReader.iterator();

            while (interator.hasNext()) {
                T obj = mapper(interator.next());

                boolean f = filter != null && filter.test(obj);
                if (filter == null || filter.test(obj)) {
                    entities.add(obj);
                }
            }
            return entities;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public T get(Predicate<T> filter) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(table.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            Iterator<String[]> interator = csvReader.iterator();

            while (interator.hasNext()) {
                T obj = mapper(interator.next());
                if (filter == null || filter.test(obj)) {
                    return obj;
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UUID> insert(List<T> items) {
        List<T> currentItems = getAll(null);
        items.forEach(x -> x.setId(UUID.randomUUID()));
        currentItems.addAll(items);
        write(currentItems);
        return items.stream().map(CsvEntity::getId).toList();
    }

    public T update(T model) {
        List<T> current = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(table.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                T obj = mapper(nextRecord);
                if (obj.getId().equals(model.getId())) {
                    current.add(model);
                    continue;
                }
                current.add(obj);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        write(current);
        return model;
    }

    public List<UUID> delete(Predicate<T> filter) {
        List<T> current = new ArrayList<>();
        List<UUID> toDelete = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(table.getPath()));
                CSVReader csvReader = new CSVReader(reader);
        ) {

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                T obj = mapper(nextRecord);
                if (filter == null || filter.test(obj)) {
                    toDelete.add(obj.getId());
                    continue;
                }
                current.add(obj);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        //if(!current.isEmpty())
            write(current);
        return toDelete;
    }
    private void write(List<T> items) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(table.getPath()))) {
            csvWriter.writeAll(items.stream().map(CsvEntity::getCsvValue).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package zelimkhan.magomadov.notes.data.note

import kotlinx.coroutines.flow.Flow
import zelimkhan.magomadov.notes.data.note.local.NoteLocal
import zelimkhan.magomadov.notes.data.note.local.NoteLocalDataSource
import zelimkhan.magomadov.notes.data.note.local.NoteTypeLocal

class NoteRepository(
    private val localDatasource: NoteLocalDataSource,
) {

    fun get(noteTypeLocal: NoteTypeLocal): Flow<List<NoteLocal>> {
        return localDatasource.get(noteTypeLocal)
    }

    suspend fun get(id: Long): NoteLocal {
        return localDatasource.get(id)
    }

    suspend fun add(): Long {
        return localDatasource.add(NoteLocal())
    }

    suspend fun update(noteLocal: NoteLocal) {
        localDatasource.update(noteLocal)
    }
}
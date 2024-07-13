package com.yasir.compose.assessment.data.mapper

import com.yasir.compose.assessment.domain.model.MedicationsClasse
import com.yasir.compose.assessment.domain.model.Medicine
import javax.inject.Inject

class MedicineViewItemMapper @Inject constructor() {

    fun map(medicationsClasses: List<MedicationsClasse>): List<Medicine> {
        return medicationsClasses.asSequence()
            .flatMap { medicationsClasse ->
                sequenceOf(
                    medicationsClasse.className.asSequence()
                        .flatMap { className ->
                            sequenceOf(
                                className.associatedDrug.asSequence()
                                    .map { drug ->
                                        Medicine(
                                            name = drug.name,
                                            dose = drug.dose,
                                            strength = drug.strength
                                        )
                                    },
                                className.associatedDrug2.asSequence()
                                    .map { drug ->
                                        Medicine(
                                            name = drug.name,
                                            dose = drug.dose,
                                            strength = drug.strength
                                        )
                                    }
                            ).flatten()
                        },
                    medicationsClasse.className2.asSequence()
                        .flatMap { className2 ->
                            sequenceOf(
                                className2.associatedDrug.asSequence()
                                    .map { drug ->
                                        Medicine(
                                            name = drug.name,
                                            dose = drug.dose,
                                            strength = drug.strength
                                        )
                                    },
                                className2.associatedDrug2.asSequence()
                                    .map { drug ->
                                        Medicine(
                                            name = drug.name,
                                            dose = drug.dose,
                                            strength = drug.strength
                                        )
                                    }
                            ).flatten()
                        }
                ).flatten()
            }
            .toList()
    }
}
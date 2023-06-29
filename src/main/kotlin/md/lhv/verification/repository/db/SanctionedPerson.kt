package md.lhv.verification.repository.db

import jakarta.persistence.*


@Entity
@Table(name = "sanctioned_persons")
data class SanctionedPerson(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(name = "name")
    var name: String = ""
)
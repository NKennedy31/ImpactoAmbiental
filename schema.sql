
    create table Contacto (
        id integer generated by default as identity (start with 1),
        email varchar(255),
        telefono varchar(255),
        primary key (id)
    )

    create table Estacion (
        id integer generated by default as identity (start with 1),
        km integer,
        primary key (id)
    )

    create table FactorDeEmision (
        id integer generated by default as identity (start with 1),
        tipoConsumo_id integer,
        primary key (id)
    )

    create table FactoresDeEmisionRepo (
        id integer generated by default as identity (start with 1),
        feCamion_id integer,
        feCarbon_id integer,
        feDiesel_id integer,
        feDistanciaMediaRecorrida_id integer,
        feElectricidad_id integer,
        feNafta_id integer,
        feNaftaConsumida_id integer,
        feUtilitario_id integer,
        fegasNatural_id integer,
        getFegasoilConsumido_id integer,
        primary key (id)
    )

    create table FactoresDeEmisionRepo_FactorDeEmision (
        FactoresDeEmisionRepo_id integer not null,
        lista_id integer not null
    )

    create table Linea (
        id integer generated by default as identity (start with 1),
        nombre varchar(255),
        primary key (id)
    )

    create table Linea_Estacion (
        Linea_id integer not null,
        estaciones_id integer not null
    )

    create table Medicion (
        id integer generated by default as identity (start with 1),
        actividad varchar(255),
        unidad varchar(255),
        valor double,
        tipoConsumo_id integer,
        primary key (id)
    )

    create table Miembro (
        id integer generated by default as identity (start with 1),
        apellido varchar(255),
        documento varchar(255),
        nombre varchar(255),
        tipo varchar(255),
        contacto_id integer,
        primary key (id)
    )

    create table Miembro_Organizacion (
        Miembro_id integer not null,
        organizaciones_id integer not null
    )

    create table Miembro_Trayecto (
        Miembro_id integer not null,
        trayectos_id integer not null
    )

    create table Organizacion (
        id integer generated by default as identity (start with 1),
        clasificacion varchar(255),
        razonSocial varchar(255),
        tipo varchar(255),
        altura integer not null,
        calle varchar(255),
        localidad integer not null,
        primary key (id)
    )

    create table Organizacion_Contacto (
        Organizacion_id integer not null,
        contactos_id integer not null
    )

    create table Sector (
        id integer generated by default as identity (start with 1),
        organizacion_id integer,
        primary key (id)
    )

    create table Sector_Miembro (
        Sector_id integer not null,
        miembros_id integer not null
    )

    create table TipoConsumo (
        id integer generated by default as identity (start with 1),
        nombre varchar(255),
        unidad varchar(255),
        primary key (id)
    )

    create table TipoConsumoRepo (
        id integer generated by default as identity (start with 1),
        camion_id integer,
        carbon_id integer,
        diesel_id integer,
        distanciaMediaRecorrida_id integer,
        electricidad_id integer,
        gasNatural_id integer,
        gasoilConsumido_id integer,
        nafta_id integer,
        naftaConsumida_id integer,
        utilitario_id integer,
        primary key (id)
    )

    create table TipoConsumoRepo_TipoConsumo (
        TipoConsumoRepo_id integer not null,
        lista_id integer not null
    )

    create table Transporte (
        tipo varchar(31) not null,
        id integer generated by default as identity (start with 1),
        combustibleXKm double,
        altura_inicio integer,
        calle_inicio varchar(255),
        localidad_inicio integer,
        altura_llegada integer,
        calle_llegada varchar(255),
        localidad_llegada integer,
        tipoConsumo_id integer,
        linea_id integer,
        llegada_id integer,
        partida_id integer,
        primary key (id)
    )

    create table Trayecto (
        id integer generated by default as identity (start with 1),
        miembro_id integer,
        primary key (id)
    )

    create table Trayecto_tramos (
        Trayecto_id integer not null,
        tramos_id integer not null
    )

    create table Usuario (
        id integer generated by default as identity (start with 1),
        clave varchar(255),
        usuario varchar(255),
        primary key (id)
    )

    create table tramos (
        id integer generated by default as identity (start with 1),
        transporte_id integer,
        primary key (id)
    )

    alter table FactoresDeEmisionRepo_FactorDeEmision 
        add constraint UK_250bcere26qtuvlhfeus7c5ph  unique (lista_id)

    alter table Linea_Estacion 
        add constraint UK_2s9yk8spb55ud5hqafti3xl2r  unique (estaciones_id)

    alter table Miembro_Organizacion 
        add constraint UK_2oqrxyqcc165nkk2jq4co4ft3  unique (organizaciones_id)

    alter table Miembro_Trayecto 
        add constraint UK_xhrxvnxm03l0snangdqucrg4  unique (trayectos_id)

    alter table Organizacion_Contacto 
        add constraint UK_j6rcx6uonr00n82n31l2igx2f  unique (contactos_id)

    alter table Sector_Miembro 
        add constraint UK_pb6wj21q906vka2wgjxdeerxk  unique (miembros_id)

    alter table TipoConsumoRepo_TipoConsumo 
        add constraint UK_kirup8cgq6qev1ihd5gorrxct  unique (lista_id)

    alter table Trayecto_tramos 
        add constraint UK_pb4bqnbub8462c9khxdy8q5ll  unique (tramos_id)

    alter table FactorDeEmision 
        add constraint FK_n8fxca5vn4ity2h309b1ud5hv 
        foreign key (tipoConsumo_id) 
        references TipoConsumo

    alter table FactoresDeEmisionRepo 
        add constraint FK_sq07sytgbo1lo0arm1kuyrcpd 
        foreign key (feCamion_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_bbeq3f9qvnicboc1i2dm309cf 
        foreign key (feCarbon_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_9bxer65x757f6t55sqfkiduxv 
        foreign key (feDiesel_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_efj8t66w9ohwtoaam8rr7pdn1 
        foreign key (feDistanciaMediaRecorrida_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_aspp6sak4kmctf0nro10uwckt 
        foreign key (feElectricidad_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_1fwih680w8tfojxld597qy328 
        foreign key (feNafta_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_d10fchrpi8via6g267wvgpp60 
        foreign key (feNaftaConsumida_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_5luk7ybrlpc623eog1bkktpkp 
        foreign key (feUtilitario_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_hkpe7aiq4jenhflgdky3x2nx2 
        foreign key (fegasNatural_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo 
        add constraint FK_jv8c5nu9lr3jutwglwftww5ej 
        foreign key (getFegasoilConsumido_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo_FactorDeEmision 
        add constraint FK_250bcere26qtuvlhfeus7c5ph 
        foreign key (lista_id) 
        references FactorDeEmision

    alter table FactoresDeEmisionRepo_FactorDeEmision 
        add constraint FK_jla67cl7do7d7bgiuyf93s3p2 
        foreign key (FactoresDeEmisionRepo_id) 
        references FactoresDeEmisionRepo

    alter table Linea_Estacion 
        add constraint FK_2s9yk8spb55ud5hqafti3xl2r 
        foreign key (estaciones_id) 
        references Estacion

    alter table Linea_Estacion 
        add constraint FK_fnh0vankfgcwtam417ttmjvnu 
        foreign key (Linea_id) 
        references Linea

    alter table Medicion 
        add constraint FK_ovicthytyl7jjxmuf4sjx8q79 
        foreign key (tipoConsumo_id) 
        references TipoConsumo

    alter table Miembro 
        add constraint FK_9ll7mq5ng1bj7vrlc1quein05 
        foreign key (contacto_id) 
        references Contacto

    alter table Miembro_Organizacion 
        add constraint FK_2oqrxyqcc165nkk2jq4co4ft3 
        foreign key (organizaciones_id) 
        references Organizacion

    alter table Miembro_Organizacion 
        add constraint FK_sykjc8pm51t1me2g9bnvhed1f 
        foreign key (Miembro_id) 
        references Miembro

    alter table Miembro_Trayecto 
        add constraint FK_xhrxvnxm03l0snangdqucrg4 
        foreign key (trayectos_id) 
        references Trayecto

    alter table Miembro_Trayecto 
        add constraint FK_t131kww33qy437i4ok3fh5i2y 
        foreign key (Miembro_id) 
        references Miembro

    alter table Organizacion_Contacto 
        add constraint FK_j6rcx6uonr00n82n31l2igx2f 
        foreign key (contactos_id) 
        references Contacto

    alter table Organizacion_Contacto 
        add constraint FK_a3xqr2uogace9rrcwy9hq4l11 
        foreign key (Organizacion_id) 
        references Organizacion

    alter table Sector 
        add constraint FK_sb9xwaiuxouphx7ju2ej3fnxq 
        foreign key (organizacion_id) 
        references Organizacion

    alter table Sector_Miembro 
        add constraint FK_pb6wj21q906vka2wgjxdeerxk 
        foreign key (miembros_id) 
        references Miembro

    alter table Sector_Miembro 
        add constraint FK_3rnyllwgufvkqj7iyg1hrd7dt 
        foreign key (Sector_id) 
        references Sector

    alter table TipoConsumoRepo 
        add constraint FK_i4o8p2wcm4bj7c3kmvt0woc3f 
        foreign key (camion_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_g1n0j2urrv1roup2quqaxk4fq 
        foreign key (carbon_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_3xlwpvtfce07m311uvcvms7s2 
        foreign key (diesel_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_s464mv86l0jaosrk6itpxkved 
        foreign key (distanciaMediaRecorrida_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_gi1fbvbpxw1jr57d4oxqbf3ol 
        foreign key (electricidad_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_ri3jqroibm1wm3w6yles1iuia 
        foreign key (gasNatural_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_63fjw9uism9ggndg7vucj9w5x 
        foreign key (gasoilConsumido_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_t8alpb0isjdgnai29b9dgnvcl 
        foreign key (nafta_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_ccvolcnig8mlohlsce7ewhr09 
        foreign key (naftaConsumida_id) 
        references TipoConsumo

    alter table TipoConsumoRepo 
        add constraint FK_na4xp7b66v9njvkr39en9mpxp 
        foreign key (utilitario_id) 
        references TipoConsumo

    alter table TipoConsumoRepo_TipoConsumo 
        add constraint FK_kirup8cgq6qev1ihd5gorrxct 
        foreign key (lista_id) 
        references TipoConsumo

    alter table TipoConsumoRepo_TipoConsumo 
        add constraint FK_of5559iwsnyahjjhwcc1smibr 
        foreign key (TipoConsumoRepo_id) 
        references TipoConsumoRepo

    alter table Transporte 
        add constraint FK_87o9psxwu5k76a3vsloxvukud 
        foreign key (tipoConsumo_id) 
        references TipoConsumo

    alter table Transporte 
        add constraint FK_pqpw1f61pw51379p7f25b8n80 
        foreign key (linea_id) 
        references Linea

    alter table Transporte 
        add constraint FK_fne6kcj8pdk5fx5hwningibbe 
        foreign key (llegada_id) 
        references Estacion

    alter table Transporte 
        add constraint FK_1abqo7c2gmh3nonm6yd39qcoh 
        foreign key (partida_id) 
        references Estacion

    alter table Trayecto 
        add constraint FK_b99fchqfslqr90ef2g6neb45b 
        foreign key (miembro_id) 
        references Miembro

    alter table Trayecto_tramos 
        add constraint FK_pb4bqnbub8462c9khxdy8q5ll 
        foreign key (tramos_id) 
        references tramos

    alter table Trayecto_tramos 
        add constraint FK_l15i5tg98kwy3a1fglgxap32s 
        foreign key (Trayecto_id) 
        references Trayecto

    alter table tramos 
        add constraint FK_9rgh2ruoj27tal469w741i8e 
        foreign key (transporte_id) 
        references Transporte

import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './sponsor.reducer';

export const Sponsor = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const sponsorList = useAppSelector(state => state.sponsor.entities);
  const loading = useAppSelector(state => state.sponsor.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="sponsor-heading" data-cy="SponsorHeading">
        <Translate contentKey="affliateMarketplaceApp.sponsor.home.title">Sponsors</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="affliateMarketplaceApp.sponsor.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/sponsor/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="affliateMarketplaceApp.sponsor.home.createLabel">Create new Sponsor</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {sponsorList && sponsorList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('sponsorName')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorName">Sponsor Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sponsorName')} />
                </th>
                <th className="hand" onClick={sort('sponsorDescription')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorDescription">Sponsor Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sponsorDescription')} />
                </th>
                <th className="hand" onClick={sort('sponsorBanner1Url')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorBanner1Url">Sponsor Banner 1 Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sponsorBanner1Url')} />
                </th>
                <th className="hand" onClick={sort('sponsorBanner2Url')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorBanner2Url">Sponsor Banner 2 Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sponsorBanner2Url')} />
                </th>
                <th className="hand" onClick={sort('sponsorBanner3Url')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorBanner3Url">Sponsor Banner 3 Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sponsorBanner3Url')} />
                </th>
                <th className="hand" onClick={sort('sponsorExternalUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorExternalUrl">Sponsor External Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sponsorExternalUrl')} />
                </th>
                <th className="hand" onClick={sort('sponsorLogoUrl')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.sponsorLogoUrl">Sponsor Logo Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sponsorLogoUrl')} />
                </th>
                <th className="hand" onClick={sort('isActive')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.isActive">Is Active</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isActive')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdOn')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.createdOn">Created On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedOn')}>
                  <Translate contentKey="affliateMarketplaceApp.sponsor.updatedOn">Updated On</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {sponsorList.map((sponsor, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/sponsor/${sponsor.id}`} color="link" size="sm">
                      {sponsor.id}
                    </Button>
                  </td>
                  <td>{sponsor.sponsorName}</td>
                  <td>{sponsor.sponsorDescription}</td>
                  <td>{sponsor.sponsorBanner1Url}</td>
                  <td>{sponsor.sponsorBanner2Url}</td>
                  <td>{sponsor.sponsorBanner3Url}</td>
                  <td>{sponsor.sponsorExternalUrl}</td>
                  <td>{sponsor.sponsorLogoUrl}</td>
                  <td>{sponsor.isActive ? 'true' : 'false'}</td>
                  <td>{sponsor.createdBy}</td>
                  <td>{sponsor.createdOn ? <TextFormat type="date" value={sponsor.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{sponsor.updatedBy}</td>
                  <td>{sponsor.updatedOn ? <TextFormat type="date" value={sponsor.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/sponsor/${sponsor.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/sponsor/${sponsor.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/sponsor/${sponsor.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="affliateMarketplaceApp.sponsor.home.notFound">No Sponsors found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Sponsor;
